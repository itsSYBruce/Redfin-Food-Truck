//we are sending it back to references.js

var mongoose = require("mongoose");


//POST - title, content
var postSchema = new mongoose.Schema({
    title: String,
    content: String
});


module.exports = mongoose.model("Post", postSchema);