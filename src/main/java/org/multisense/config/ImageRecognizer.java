package org.multisense.config;

import org.multisense.pojo.InferenceResult;
import org.springframework.stereotype.Component;

@Component
public class ImageRecognizer {
    public InferenceResult predict(String base64Image) {
        // 解析图片、做 ONNX 推理
        // 可用 ONNXRuntime session.run()
        return new InferenceResult("cat", 0.987);
    }
}
