package dev.danvega.contentcalendar.repository;

import dev.danvega.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {
}
