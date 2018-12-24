var express = require("express");
var app = express();
var mongoose = require("mongoose");
var passport = require("passport");
var bodyParser = require("body-parser");
var LocalStrategy = require("passport-local");
var passportLocalMongoose = require("passport-local-mongoose");
var User = require("./models/user");

mongoose.connect("mongodb://localhost/auth_demo_app", { useNewUrlParser: true });
app.set("view engine", "ejs");

//express session
app.use(require("express-session")({
    secret: "heeeeey oh tuna",
    resave: false,
    saveUninitialized: false
}));


//to passport auth 
passport.use(new LocalStrategy(User.authenticate()));
app.use(passport.initialize());
app.use(passport.session());
app.use(bodyParser.urlencoded({extended: true}));

//reading and putting back information from the session
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());


//============
// ROUTES
//============



app.get("/", function(req, res) {
    res.render("home");
})


app.get("/secret", isLoggedIN, function(req, res) {
    res.render("secret");
})



//============
//Auth routes
//============

app.get("/register", function(req, res) {
   res.render("register"); 
});

//handle user signup
app.post("/register", function(req, res) {
    req.body.username
    req.body.password
    User.register(new User({username: req.body.username}), req.body.password, function(err, user) {
        if (err) {
            console.log(err);
            return res.render("register");
        }
        else {
            passport.authenticate("local")(req, res, function() {
               res.redirect("/secret"); 
            });
        }
    });
})

//LOGIN ROUTES
//render login form
app.get("/login", function(req, res) {
    res.render("login");
})

//login logic
//middleware
app.post("/login", passport.authenticate("local", {
    successRedirect: "/secret",
    failureRedirect: "/login"
}), function(req, res) {
});


//logout
app.get("/logout", function(req, res) {
    req.logout();
    res.redirect("/");
})

function isLoggedIN(req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }
    res.redirect("/login");
}

app.listen(process.env.PORT, process.env.IP, function() {
    console.log("server's up");
})