package com.company.bonsai;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.company.bonsai.ui.SwingDesktopUI;
import javax.swing.SwingUtilities;

public class DesktopAppBuilder {

    private static final String CONFIG_PATH = "bonsai-desktop-spring-config.xml";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_PATH);
        SwingDesktopUI swingDesktopUI = context.getBean(SwingDesktopUI.class);
        SwingUtilities.invokeLater(swingDesktopUI);
    }

}
