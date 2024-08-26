package com.nihon.shiken;

import com.nihon.shiken.model.mKanji;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ShikenApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShikenApplication.class, args);

		}

}
