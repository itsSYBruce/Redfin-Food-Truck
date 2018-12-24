var mongoose = require("mongoose");
mongoose.connect("mongodb://localhost/blog_demo", { useNewUrlParser: true });

//POST - title, content
var postSchema = new mongoose.Schema({
    title: String,
    content: String
});

var Post = mongoose.model("Post", postSchema);


//USER - emails, name
var userSchema = new mongoose.Schema({
    email: String,
    name: String,
    posts: [postSchema]
});

var User = mongoose.model("User", userSchema);






// var newUser= new User({
//     email: "itssybruce@berkeley.edu",
//     name: "bear bear"
// });

// newUser.posts.push({
//     title: "This is the big bear",
//     content: "oh yea hi"
// });

// newUser.save(function(err, user) {
//     if (err) {
//         console.log(err);
//     }
//     else {
//         console.log(user);
//     }
// })


User.findOne({name: "bear bear"}, function(err, user) {
    if (err) {
        console.log(err);
    } else {
        user.posts.push({
            title: "3 things that I hate the most",
            content: "fish fish fisher"
        });
        user.save(function(err, user) {
            if (err) {
                console.log(err);
            }
            else {
                console.log(user);
            }
        })
    }
})



// var newPost = new Post({
//     title: "Reflection on apples",
//     content: "they are delicious"
// })

// newPost.save(function(err, post) {
//     if (err) {
//         console.log(err);
//     }else {
//         console.log(post);
//     }
// })