package com.ecommerce.template.common.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.ecommerce.template")
public class UserArchTest {

    @ArchTest
    ArchRule 레이어계층_아키텍처_테스트 = layeredArchitecture()
            .layer("Controller").definedBy("..controller..")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .layer("Facade").definedBy("..facade..")
            .whereLayer("Facade").mayOnlyBeAccessedByLayers("Controller")
            .layer("Service").definedBy("..service..")
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Facade")
            .layer("Adapter").definedBy("..adapter..")
            .whereLayer("Adapter").mayOnlyBeAccessedByLayers("Service")
            .layer("Repository").definedBy("..repository..")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Adapter")
            ;

    @ArchTest
    ArchRule Autowired_어노테이션은_사용하지_않는다 = noFields()
            .should().beAnnotatedWith(Autowired.class);
}
