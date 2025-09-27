package com.mercadona.alejandro.dev.application.ports.driving;
import java.util.Optional;
import com.mercadona.alejandro.dev.domain.Example;
import com.mercadona.framework.cna.commons.domain.MercadonaPage;
import com.mercadona.alejandro.dev.application.exceptions.ExampleNotFoundException;

public interface ExampleServicePort {

  MercadonaPage<Example> getAllExamples(Integer pageNumber, Integer pageSize, String sort);

  Optional<Example> getExample(Long id) throws ExampleNotFoundException;

  Example createExample(Example example);

  Example updateExample(Long id, Example exampleUpdate);

  void deleteExample(Long id);

}
