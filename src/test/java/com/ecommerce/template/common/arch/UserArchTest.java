package com.ecommerce.template.common.arch;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
    public void 각_레이어_계층은_허용된_계층외에는_접근할_수_없다() {
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
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Adapter")
            .layer("Dto").definedBy(new DescribedPredicate<>("All dto except common dto") {
                @Override
                public boolean test(JavaClass javaClass) {
                    return !javaClass.getPackageName().contains("com.ecommerce.template.common")
                            && javaClass.getPackageName().contains("dto");
                }
            })
            .whereLayer("Dto").mayOnlyBeAccessedByLayers("Controller")
            ;


        layeredArchitecture.check(classes);
    }

    @Test
    public void Autowird_어노테이션은_사용하지_않는다() {
        ArchRule archRule = noFields()
                .should().beAnnotatedWith(Autowired.class);

        archRule.check(classes);
    }

    @Test
    public void DTO_레이어의_빌더를_제외한_모든_생성자는_private_해야한다() {
        ArchRule archRule = ArchRuleDefinition.classes()
                .that()
                .resideInAPackage("com.ecommerce.template.*.dto..")
                .and()
                .haveSimpleNameNotEndingWith("Builder")
                .should()
                .haveOnlyPrivateConstructors();

        archRule.check(classes);
    }

    @Test
    public void 도메인_레이어의_빌더를_제외한_모든_생성자는_private_해야한다() {
        ArchRule archRule = ArchRuleDefinition.classes()
                .that()
                .resideInAPackage("com.ecommerce.template.*.domain..")
                .and()
                .haveSimpleNameNotEndingWith("Builder")
                .should()
                .haveOnlyPrivateConstructors();

        archRule.check(classes);
    }
}
