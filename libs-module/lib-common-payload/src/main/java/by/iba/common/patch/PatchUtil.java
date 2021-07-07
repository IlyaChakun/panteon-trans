package by.iba.common.patch;


import by.iba.common.domain.core.BaseEntity;
import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class PatchUtil {

    public static <R extends BaseEntity> R applyPatchToRequest(PatchReq patch, R request) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            JsonNode node = objectMapper.convertValue(request, JsonNode.class);
            JsonNode patched = patch.getPatch().apply(node);

            return objectMapper.readerForUpdating(request).readValue(patched);

        } catch (JsonPatchException | IOException e) {
            throw new ServiceException(HttpStatus.BAD_GATEWAY.value(), "exception.request.cannot_patch_request");
        }
    }

}
