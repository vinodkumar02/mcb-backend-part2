package com.mcb.part2.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcb.part2.constants.ResponseMessageConstant;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
   
	@Autowired
	private MessageSource messageSource;
	@Bean
    public Docket productApi() {
    	ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")                 // name of header
                         .modelRef(new ModelRef("string"))
                         .parameterType("header")               // type - header
                         .defaultValue("")        // based64 of - zone:mypassword
                         .required(false)                // for compulsory
                         .build();
        
        List<springfox.documentation.service.Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());   
		
		
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.mcb.part2"))
            .paths(PathSelectors.regex("/.*"))
            .build().pathMapping("").globalOperationParameters(aParameters);
    }
   
	  
	
	@PostConstruct
	public void init()
	{
		for(ResponseMessageConstant errorCode:ResponseMessageConstant.values())
		{
			errorCode.setMessageSource(messageSource);
		}
	}
	
}