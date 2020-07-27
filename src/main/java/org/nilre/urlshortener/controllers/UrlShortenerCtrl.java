package org.nilre.urlshortener.controllers;

import org.nilre.urlshortener.services.IUrlShortenerService;
import org.nilre.urlshortener.error.ApplicationException;
import org.nilre.urlshortener.shorteners.validation.Validator;
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

    private Validator validator;

    public UrlShortenerCtrl(IUrlShortenerService shortenerService, Validator validator) {
        this.shortenerService = shortenerService;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<Map> getShorterUrl(@RequestParam("url") String url) {
        return ResponseEntity.ok(Map.of("alias", shortenerService.getShorterUrl(url)));
    }

    @GetMapping("/{alias}")
    public ResponseEntity<Void> getUrl(@PathVariable("alias") String alias) {
        if (validator.validShortUrl(alias)) {
            String originalUrl = shortenerService.getOriginalUrl(alias);
            if (originalUrl != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(originalUrl));
                return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
            }
            throw new ApplicationException("That 'alias' is not associated with any original Url!!");
        } else {
            throw new ApplicationException("Invalid 'alias' format");
        }
    }
}
