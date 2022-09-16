fun main(args: Array<String>) {

    // создаем пост 1
    var newComment = Comment(1)
    var newLike = Like(1, true, false)
    var newPost = Post(comments = newComment, likes = newLike)
    wallService.addPost(newPost)
    wallService.printPosts()

    // создаем пост 2
    newComment = Comment(10)
    newLike = Like(10, true, false)
    newPost = Post(comments = newComment, likes = newLike)
    wallService.addPost(newPost)
    wallService.printPosts()

    // создаем пост 3
    newComment = Comment(20)
    newLike = Like(20, true, true)
    newPost = Post(comments = newComment, likes = newLike, views = 144)
    wallService.addPost(newPost)
    wallService.printPosts()

    // обновляем пост 2 (меняем количество комментариев, лайков и просмотров)
    newComment = Comment(100)
    newLike = Like(30, userLikes = true)
    newPost = Post(2, comments = newComment, likes = newLike, views = 214)
    if (!wallService.updatePost(newPost)) println("Пост не найден.")
    wallService.printPosts()

}

data class Post (
    val id: Int = 0,
    val ownerId: Int = 12345,
    val publicDate: String = "12.09.2022",
    val content: String = "It's test content",
    val friendsOnly: Boolean = false,
    val comments: Comment,
    val likes: Like,
    val views: Int = 124,
    val isAds: Boolean = false,
        )

data class Comment (
    val count: Int = 0,
    val canOpen: Boolean = true,
    val canComment: Boolean = true
        )

data class Like (
    val count: Int,
    val canLike: Boolean = true,
    val userLikes: Boolean
        )

object wallService {
    var posts = emptyArray<Post>()
    private var lastPostId: Int = 0

    // добавление поста в хранилище
    fun addPost (post: Post): Post {
        lastPostId++
        println("\nСоздаем пост. Номер поста - $lastPostId.")
        posts += post.copy(id = lastPostId)
        return posts.last()
    }

    // внесение изменений в существующий пост
    fun updatePost (post: Post): Boolean {
        println("\nОбновляем пост #${post.id}")
        for ((index) in posts.withIndex()) {
            if (posts[index].id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    // проверочная печать массива постов
    fun printPosts() {
        for (post in posts) {
            println("----------------")
            println("ID поста: ${post.id}, ID автора: ${post.ownerId}, опубликовано: ${post.publicDate}")
            println("Текст поста: ${post.content}")
            println("Комментариев: ${post.comments.count}, лайков: ${post.likes.count}, просмотров: ${post.views}")
        }
    }

    fun clear() {
        posts = emptyArray()
        lastPostId = 0
    }

}

