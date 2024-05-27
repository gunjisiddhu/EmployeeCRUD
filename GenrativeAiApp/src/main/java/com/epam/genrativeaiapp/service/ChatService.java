package com.epam.genrativeaiapp.service;

import com.epam.genrativeaiapp.dto.InputDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient aiClient;

    public static final String WRITE_ME_HAIKU_ABOUT_CAT = """
            Write me Haiku about cat,
            haiku should start with the word cat obligatory""";


    String promptString = """
            Explain about {keyword} in {technology}
            {format}
                     """;
    PromptTemplate promptTemplate = new PromptTemplate(promptString);


    public InputDataDTO getInfoOnData( String keyword,  String technology) {

        BeanOutputParser<InputDataDTO> poetryDtoBeanOutputParser = new BeanOutputParser<>(InputDataDTO.class);
        promptString = """
            Explain about {keyword} in {technology}
            {format}
                     """;

        promptTemplate.add("keyword",keyword);
        promptTemplate.add("technology",technology);


        promptTemplate.add("format", poetryDtoBeanOutputParser.getFormat());
        promptTemplate.setOutputParser(poetryDtoBeanOutputParser);


        ChatResponse response = aiClient.generate(promptTemplate.create());

        return poetryDtoBeanOutputParser.parse(response.getGeneration().getContent());
    }


    public PoetryDto getPoetryByGenreAndTheme(String genre, String theme) {
        BeanOutputParser<PoetryDto> poetryDtoBeanOutputParser = new BeanOutputParser<>(PoetryDto.class);

        promptString = """
        Write me {genre} poetry about {theme}
        {format}
    """;

        promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("genre", genre);
        promptTemplate.add("theme", theme);
        promptTemplate.add("format", poetryDtoBeanOutputParser.getFormat());
        promptTemplate.setOutputParser(poetryDtoBeanOutputParser);

        ChatResponse response = aiClient.generate(promptTemplate.create());

        return poetryDtoBeanOutputParser.parse(response.getGeneration().getContent());
    }




}
