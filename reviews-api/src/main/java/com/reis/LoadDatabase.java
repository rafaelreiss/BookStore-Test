package com.reis;

import com.reis.course.Course;
import com.reis.course.CourseRepository;
import com.reis.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by reis on 8/20/2016.
 */
@Component
public class LoadDatabase implements ApplicationRunner{

    private final CourseRepository courses;

    @Autowired
    public LoadDatabase(CourseRepository courses){
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args)throws Exception{
        Course course = new Course("iOS Basics", "http://iosBasics.com");
        course.addReview(new Review(4, "Great course"));
        courses.save(course);

        String[] templates = {
            "Up and Running with %s",
             "%s Basics",
             "%s for Beginners",
             "Development with %s"
        };

        String[] buzzwords ={
            "Spring REST Data",
             "Java 8",
             "Python",
             "Hibernate",
             "Groovy",
             "Spring HATEOAS"
        };
        List<Course> listOfCousers = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            String template = templates[i % templates.length];
            String buzzword = buzzwords[i % buzzwords.length];
            String title = String.format(template, buzzword);
            Course c = new Course(title, "http://www.test.com");
            c.addReview(new Review(i % 5, String.format("More %s please!", buzzword)));
            listOfCousers.add(c);
        });

        courses.save(listOfCousers);
    }
}
