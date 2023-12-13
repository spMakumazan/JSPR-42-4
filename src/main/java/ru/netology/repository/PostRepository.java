package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {

    private final Map<Long, Post> postMap;
    private AtomicLong currentId;

    public PostRepository() {
        postMap = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return new ArrayList<>(postMap.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postMap.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(currentId.incrementAndGet());
        }
        postMap.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        postMap.remove(id);
    }
}
