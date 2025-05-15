package org.multisense.controller;

import org.multisense.config.ImageRecognizer;
import org.multisense.pojo.InferenceRequest;
import org.multisense.pojo.InferenceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api")
class InferenceController {

    private final ImageRecognizer imageRecognizer;

    @Autowired
    public InferenceController(ImageRecognizer imageRecognizer) {
        this.imageRecognizer = imageRecognizer;
    }

    @PostMapping("/predict")
    public Mono<InferenceResult> predict(@RequestBody Mono<InferenceRequest> requestMono) {
        return requestMono.flatMap(req -> Mono.fromCallable(() ->
                        imageRecognizer.predict(req.base64Image())
                ).subscribeOn(Schedulers.boundedElastic())
        );
    }
}
