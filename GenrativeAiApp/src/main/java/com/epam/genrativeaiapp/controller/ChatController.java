package com.epam.genrativeaiapp.controller;


import com.epam.genrativeaiapp.dto.InputDataDTO;
import com.epam.genrativeaiapp.service.ChatService;
import com.epam.genrativeaiapp.service.PoetryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {


    private final ChatService chatService;

    @PostMapping("/gimmePoem")
    ResponseEntity<PoetryDto> genPoem(@RequestParam String genre, @RequestParam String theme){
        return new ResponseEntity<>(chatService.getPoetryByGenreAndTheme(genre, theme), HttpStatus.OK);
    }

    @PostMapping("/gimmeTechInfo")
    ResponseEntity<InputDataDTO> getTechnicalInfo(@RequestParam String keyword, @RequestParam String technology){
        return new ResponseEntity<>(chatService.getInfoOnData(keyword, technology), HttpStatus.OK);
    }

}
