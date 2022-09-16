import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class wallServiceTest {

    @Before
    fun clearBeforeTest() {
        wallService.clear()
    }

    @Test
    fun addPost() {
        var newComment = Comment(1)
        var newLike = Like(1, true, false)
        var newPost = Post(comments = newComment, likes = newLike)
        val result = wallService.addPost(newPost).id

        assertEquals(1, result)
    }

    @Test
    fun updatePost() {
        var newComment = Comment(1)
        var newLike = Like(1, true, false)
        var newPost = Post(comments = newComment, likes = newLike)
        wallService.addPost(newPost)

        newComment = Comment(5)
        newLike = Like(4, true, false)
        newPost = Post(comments = newComment, likes = newLike)
        wallService.addPost(newPost)

        newPost = Post(id = 1, comments = newComment, likes = newLike, views = 21)
        var result = wallService.updatePost(newPost)

        assertTrue(result)

        // несуществующий ID
        newPost = Post(id = 3, comments = newComment, likes = newLike, views = 21)
        result = wallService.updatePost(newPost)

        assertFalse(result)

    }
}