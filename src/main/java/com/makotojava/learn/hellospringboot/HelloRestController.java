package com.makotojava.learn.hellospringboot;

/*
 * Copyright 2017 Makoto Consulting Group, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController("/spring")
public class HelloRestController {

  @RequestMapping("/hello")
  public String hello() {
    MysqlUtil.insertOrUpdateOrdelete();
    return "Hello. All your base are belong to us.";
  }
  @RequestMapping("/hello1")
  public ArrayList<HashMap<String,Object>> hello1() {
    return MysqlUtil.query("select * from weather_ther");
//    return "Hello. All your base are belong to us.";
  }
  @RequestMapping("/hello2")
  public ArrayList hello2() {
    ArrayList<String> arr = new ArrayList<String>(){{
      this.add("ggg");
      this.add("ggg");
      this.add("ggg");
      this.add("ggg");
      this.add("ggg");
    }};
    return arr;
  }
}
