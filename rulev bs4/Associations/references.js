var mongoose = require("mongoose");
mongoose.connect("mongodb://localhost/blog_demo_2", { useNewUrlParser: true });

var Post = require("./models/post");

var User = require("./models/user");


//create post, push it and save it to the user
// Post.create({
//     title: "how to cook the best burger pt. 2",
//     content: "hey dooooood how ya doing"
// }, function(err, post) {
//     if (err) {
//         console.log(err);
//     } else {
//     User.findOne({email: "bob@gamil.com"}, function(err, foundUser) {
//         if (err) {
//             console.log(err);
//         }
//         else {
//             foundUser.posts.push(post);
//             foundUser.save(function(err, data) {
//                 if (err) {
//                     console.log(err);
//                 }
//                 else {
//                     console.log(data);
//                 }
//             });
//         }
//     })
// }
// }
// );



//Find user
//find all posts for that user, retrieve the data
//populate here
User.findOne({email: "bob@gamil.com"}).populate("posts").exec(function(err, user) {
    if (err) {
        console.log(err);
    }
    else {
        console.log(user);
    }
})



//create a user manually to check the usability
// User.create({
//     email: "bob@gamil.com",
//     name: "Bob Bobbyhead"
// });