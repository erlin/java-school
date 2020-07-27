package org.nilre.urlshortener.controllers;

import org.nilre.urlshortener.services.IUrlShortenerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UrlShortenerCtrl {

    private IUrlShortenerService shortenerService;

    public UrlShortenerCtrl(IUrlShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping
    public ResponseEntity<Map> getShorterUrl(@RequestParam("url") String url) {
        return ResponseEntity.ok(Map.of("alias", shortenerService.getShorterUrl(url)));
    }

    @GetMapping("/{alias}")
    public ResponseEntity<Void> getUrl(@PathVariable("alias") String alias) {
        String originalUrl = shortenerService.getOriginalUrl(alias);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
