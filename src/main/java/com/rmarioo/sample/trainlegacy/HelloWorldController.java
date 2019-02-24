package com.rmarioo.sample.trainlegacy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Hello world controller", description="A sample hello world controller")
@Controller
public class HelloWorldController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @ApiOperation(value = "Return a simple greeting message", response = Greeting.class)
  @GetMapping("/hello-world")
  @ResponseBody
  public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

}