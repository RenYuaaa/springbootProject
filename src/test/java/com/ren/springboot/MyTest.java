package com.ren.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    RedisTemplate redisTemplate;

    private static String  salt = "!Q@W#E$RRT%TT^YY";

    @Test
    public void testMd5() {

        String md5 = DigestUtils.md5DigestAsHex("123456".getBytes());
        BASE64Encoder base64Encoder =new BASE64Encoder();

        String encode = base64Encoder.encode((md5).getBytes());
        System.out.println(encode);
    }

    @Test
    public void filterTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.filter(s -> s % 2 == 0).forEach(System.out::println);
    }

    @Test
    public void mapTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> collect = stream.map(s -> String.valueOf(s)).collect(Collectors.toList());
    }

    @Test
    public void dinstinctTest() {
        Stream<Integer> stream = Stream.of(1, 2, 2, 4, 3, 5, 3, 3, 4, 9);
        stream.distinct().forEach(System.out::println);
    }

    @Test
    public void sortTest() {
        Stream<Integer> stream = Stream.of(2, 1, 4, 6, 3, 5, 3, 8, 9, 10);
        stream.distinct().sorted((x, z) -> x > z ? -1 : 1).forEach(System.out::println);
    }

    @Test
    public void peekTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //TODO: 这里的peek方法中，最后是不是一定要跟一个collect()方法？
        stream.peek(s -> System.out.println(s)).collect(Collectors.toList());
    }

    @Test
    public void limitTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.limit(3).forEach(System.out::println);
    }

    @Test
    public void skipTeat() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.skip(3).forEach(System.out::println);
    }

    @Test
    public void collectorTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void collectToListTest() {
        List<String> strings = Arrays.asList("java", "C", "C#", "C++");
        LinkedList<String> collect = strings.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collect);
    }

    @Test
    public void joiningTest() {
        List<String> strings = Arrays.asList("java", "C", "C++", "C#");
        String collect = strings.stream().collect(Collectors.joining(":", "123", "123"));
        System.out.println(collect);
    }

    @Test
    public void mappingTest() {
        //TODO: 这个方法不会用   回头再看看
        List<String> strings = Arrays.asList("java", "C", "C++", "C#");
        strings.stream().collect(Collectors.mapping(String::getBytes, Collectors.toList()));
    }

    @Test
    public void collectAndThenTest() {
        //TODO: 这个方法不会用   回头再看看
        List<String> strings = Arrays.asList("java", "C", "C++", "C#");
//        strings.stream().collect(Collectors.collectingAndThen())
    }

    @Test
    public void countingTest() {
        List<String> strings = Arrays.asList("java", "C", "C++", "C#");
        Long collect = strings.stream().collect(Collectors.counting());
        System.out.println(collect);
    }

//    @Test
//    public void redisTest() {
//        redisTemplate.opsForValue().set("aaa", "111");
//    }

}
