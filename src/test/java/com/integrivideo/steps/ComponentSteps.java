package com.integrivideo.steps;

import com.integrivideo.Component;
import com.integrivideo.pages.ComponentListPage;
import com.integrivideo.pages.ComponentTypeEnum;
import com.integrivideo.pages.CreateComponentPage;
import com.integrivideo.pages.ProjectDetailsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class ComponentSteps extends ScenarioSteps {

    private static final Logger LOGGER = Logger.getLogger(ComponentSteps.class.getName());

    private ComponentListPage componentListPage;

    private CreateComponentPage createComponentPage;

    private ProjectDetailsPage projectDetailsPage;

    @Step
    public void openCreateComponentPage() {
        componentListPage.clickAddComponent();
    }

    @Step
    public void createComponent(ComponentTypeEnum componentType, String name) {
        createComponentPage.fillInForm(componentType, name);
        createComponentPage.clickCreateButton();
    }

    @Step
    public void numberOfComponentsShouldBeEqualTo(long number) {
        assertThat(number == componentListPage.getComponentCount());
    }

    public long getComponentCount() {
        return componentListPage.getComponentCount();
    }

    public void openComponentPage(final long componentNumberInList) {
        componentListPage.openComponentPage(componentNumberInList);
    }

    public void copyJsCode() { createComponentPage.copyJsCode(); }

    public void componentDetailsShouldBeLike(final ComponentTypeEnum componentTypeEnum, final String name) {
        Component component = createComponentPage.getComponentDetails();
        assertThat(component.getName()).isEqualToIgnoringCase(name);
        assertThat(component.getComponentType()).isEqualTo(componentTypeEnum);
    }

    public void editComponent(String name) {
        createComponentPage.fillInForm(null, name);
        createComponentPage.clickUpdateButton();
    }

    public void validateJsCode() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String jsFromClipboard = null;
        try {
            jsFromClipboard = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Content of clipboard: " + jsFromClipboard);
        assertThat(jsFromClipboard).isEqualTo(createComponentPage.getJsCode());
    }

    public void returnToProject() {
        createComponentPage.returnToProject();
    }
}
