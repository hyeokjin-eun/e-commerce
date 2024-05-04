package com.ecommerce.template.common.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@DisplayName("공통_아키텍처_테스트")
@Order(1)
public class UserArchTest {

    private JavaClasses classes;

    @BeforeEach
    public void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.ecommerce.template");
    }

    @Test
    public void 레이어드_아키텍처_테스트() {
        Architectures.LayeredArchitecture layeredArchitecture = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Controller").definedBy("..controller..")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .layer("Facade").definedBy("..facade..")
            .whereLayer("Facade").mayOnlyBeAccessedByLayers("Controller")
            .layer("Service").definedBy("..service..")
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Facade")
            .layer("Adapter").definedBy("..adapter..")
            .whereLayer("Adapter").mayOnlyBeAccessedByLayers("Service")
            .layer("Repository").definedBy("..repository..")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Adapter");

        layeredArchitecture.check(classes);
    }

    @Test
    public void Autowird_주입_여부_테스트() {
        ArchRule archRule = noFields()
                .should().beAnnotatedWith(Autowired.class);

        archRule.check(classes);
    }
}
