package vn.common.specs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qunguyen
 */
@Repository("in_memory_specification_repo")
public class InMemorySpecificationRepository implements SpecificationRepository {

    private static final Logger LOG = LoggerFactory.getLogger(InMemorySpecificationRepository.class);

    private Map<String, String> specsMap = new HashMap<>();
    ObjectMapper objectMapper;

    public InMemorySpecificationRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    @Override
    public void save(SpecificationEntity entity) {
        try {
            String json = this.objectMapper.writeValueAsString(entity);
            this.specsMap.put(entity.id(), json);
            LOG.debug("Serialized json: {}", json);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Could not serialize specification", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> SpecificationEntity<T> getById(String id) {
        try {
            String json = this.specsMap.get(id);
            return this.objectMapper.readValue(json, SpecificationEntity.class);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not deserialize specification", e);
        }
    }
}
