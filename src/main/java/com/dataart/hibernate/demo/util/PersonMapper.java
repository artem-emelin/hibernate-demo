package com.dataart.hibernate.demo.util;

import com.dataart.hibernate.demo.database.entities.Person;
import com.dataart.hibernate.demo.model.PersonModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonModel toPersonModel(Person person);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "status", source = "status")
    void update(@MappingTarget Person repositoryPerson, PersonModel personModel);
}
