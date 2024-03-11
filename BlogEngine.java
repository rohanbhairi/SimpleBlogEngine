import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class Comment {
    private User user;
    private String text;

    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}

class Post {
    private String title;
    private String content;
    private List<Comment> comments;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void addComment(User user, String text) {
        Comment comment = new Comment(user, text);
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}

class Blog {
    private List<Post> posts;

    public Blog() {
        this.posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }
}

public class BlogEngine {
    public static void main(String[] args) {
        Blog blog = new Blog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create a new post");
            System.out.println("2. View all posts");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter post title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter post content: ");
                    String content = scanner.nextLine();
                    User author = new User("Anonymous"); // For simplicity, use an anonymous user
                    Post newPost = new Post(title, content);
                    blog.addPost(newPost);

                    System.out.print("Add a comment: ");
                    String commentText = scanner.nextLine();
                    newPost.addComment(author, commentText);

                    System.out.println("Post created successfully!");
                    break;
                case 2:
                    List<Post> allPosts = blog.getPosts();
                    if (allPosts.isEmpty()) {
                        System.out.println("No posts available.");
                    } else {
                        System.out.println("All Posts:");
                        for (Post post : allPosts) {
                            System.out.println("Title: " + post.getTitle());
                            System.out.println("Content: " + post.getContent());
                            List<Comment> comments = post.getComments();
                            if (!comments.isEmpty()) {
                                System.out.println("Comments:");
                                for (Comment comment : comments) {
                                    System.out.println("- " + comment.getUser().getUsername() + ": " + comment.getText());
                                }
                            }
                            System.out.println();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the blog engine. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}