/*
 * Copyright (C) 2005-2015 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.po.share.cloud;

import org.alfresco.po.HtmlPage;
import org.alfresco.po.share.FactoryPage;
import org.alfresco.po.share.FactorySharePage;
import org.alfresco.po.share.ShareDialogue;
import org.alfresco.po.share.SharePopup;
import org.alfresco.po.share.admin.AdminConsolePage;
import org.alfresco.po.share.cloud.user.AdminConsoleCloudPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * FactoryShareCMMPage implements the pageFactory FactorySharePage in share-po.
 * 
 * @author Meenal Bhave
 * @author Richard Smith
 * @since version 1.0
 */
public class FactoryShareCloudPage extends FactorySharePage implements FactoryPage
{
    private static final Log LOGGER = LogFactory.getLog(FactoryShareCloudPage.class);

    
    private static final String CMM_URL = "custom-model-manager";

    private static final String TPG_HASH = "view=types_property_groups";

    private static final String PROPERTIES_HASH = "view=properties";

    private static final String FORM_EDITOR_HASH = "view=editor";

    private static final String VIEW_MODELS_HASH = "view=models";

    protected static final String SHARE_DIALOGUE_AIKAU = "div.dijitDialogTitleBar";

    /**
     * Instantiates a new factory share cmm page.
     */
    public FactoryShareCloudPage()
    {
        super();
        pages.put("admin-console", AdminConsoleCloudPage.class);
    }

    /*
     * (non-Javadoc)
     * @see org.alfresco.po.share.FactorySharePage#getPage(org.alfresco.webdrone.WebDrone)
     */
    @Override
    public HtmlPage getPage(final WebDriver driver)
    {
        HtmlPage page = resolvePage(driver);

        // check for Share Error Popup
        if (page instanceof SharePopup)
        {
            LOGGER.info("SharePopup");
            return page;
        }

        // check for ShareDialogue
        if (page instanceof ShareDialogue)
        {
            LOGGER.info("shareDialogue");
            return page;
        }
        else
        {
            try
            {
                WebElement shareDialogue = driver.findElement(By.cssSelector(SHARE_DIALOGUE_AIKAU));
                if (shareDialogue.isDisplayed())
                {
                    LOGGER.info(shareDialogue.getText());
                    return resolveCloudDialoguePage(driver, page);
                }
            }
            catch (Exception e)
            {
                LOGGER.debug("No Share Dialogue (Aikau Style) open: ", e);
            }
        }

        // check for SharePage
        if (driver.getCurrentUrl().contains(TPG_HASH))
        {
            return instantiatePage(driver, pages.get("ManageTypesAndAspects"));
        }
        else if (driver.getCurrentUrl().contains(PROPERTIES_HASH))
        {
            return instantiatePage(driver, pages.get("ManageProperties"));
        }
        else if (driver.getCurrentUrl().contains(FORM_EDITOR_HASH))
        {
            return instantiatePage(driver, pages.get("FormEditor"));
        }
        else if (driver.getCurrentUrl().endsWith(CMM_URL) || driver.getCurrentUrl().endsWith(VIEW_MODELS_HASH))
        {
            return instantiatePage(driver, pages.get("ModelManager"));
        }
        else if (page instanceof AdminConsolePage)
        {
            return new AdminConsoleCloudPage(driver);
        }
        else
        {
            return page;
        }
    }

    /**
     * Resolve share cmm dialogue page.
     * 
     * @param factoryPage the drone
     * @return the html page
     */
    private static HtmlPage resolveCloudDialoguePage(WebDriver driver, HtmlPage page)
    {
        HtmlPage sharePage = page;

        try
        {
            WebElement dialogue = driver.findElement(By.cssSelector(SHARE_DIALOGUE_AIKAU));

            if (dialogue != null && dialogue.isDisplayed())
            {
                String dialogueText = dialogue.getText();
            }
        }
        catch (NoSuchElementException nse)
        {
            LOGGER.debug("No Share Dialogue (Aikau Style) open: ", nse);
        }

        return sharePage;
    }
}