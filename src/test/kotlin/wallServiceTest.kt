import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPost() {
        val newComment = Comment(1)
        val newLike = Like(1, true, false)
        val newPost = Post(comments = newComment, likes = newLike)
        val result = WallService.addPost(newPost).id

        assertEquals(1, result)
    }

    @Test
    fun updatePostSuccess() {
        var newComment = Comment(1)
        var newLike = Like(1, true, false)
        var newPost = Post(comments = newComment, likes = newLike)
        WallService.addPost(newPost)

        newComment = Comment(5)
        newLike = Like(4, true, false)
        newPost = Post(comments = newComment, likes = newLike)
        WallService.addPost(newPost)

        newPost = Post(id = 1, comments = newComment, likes = newLike, views = 21)
        val result = WallService.updatePost(newPost)

        assertTrue(result)

    }

    @Test
    fun updatePostFailure() {
        var newComment = Comment(1)
        var newLike = Like(1, true, false)
        var newPost = Post(comments = newComment, likes = newLike)
        WallService.addPost(newPost)

        newComment = Comment(5)
        newLike = Like(4, true, false)
        newPost = Post(comments = newComment, likes = newLike)
        WallService.addPost(newPost)

        newPost = Post(id = 3, comments = newComment, likes = newLike, views = 21)
        val result = WallService.updatePost(newPost)

        assertFalse(result)

    }

}