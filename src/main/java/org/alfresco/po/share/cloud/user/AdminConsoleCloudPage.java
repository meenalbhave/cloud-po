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

package org.alfresco.po.share.cloud.user;

import java.util.List;

import org.alfresco.po.HtmlPage;
import org.alfresco.po.RenderTime;
import org.alfresco.po.exception.PageException;
import org.alfresco.po.share.admin.AdminConsolePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * PageObject Class for AccountSettingsPage with CMM option
 * 
 * @author Meenal Bhave
 */

public class AdminConsoleCloudPage extends AdminConsolePage
{
    private Log logger = LogFactory.getLog(this.getClass());

    private static final String pageTitle = "Model Manager";

    private static final By CMM_LINK = By.cssSelector("a[href='custom-model-manager']");

    // Constructor
    public AdminConsoleCloudPage(WebDriver drone)
    {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public AdminConsoleCloudPage render(RenderTime timer)
    {
        basicRender(timer);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AdminConsoleCloudPage render()
    {
        return render(new RenderTime(maxPageLoadingTime));
    }

    @SuppressWarnings("unchecked")
    public AdminConsoleCloudPage render(final long time)
    {
        return render(new RenderTime(time));
    }

    /**
     * Clicks on Manage Custom Models link.
     * 
     * @return {@link HtmlPage}
     */
    public HtmlPage selectCustomModelManager()
    {
        try
        {
          logger.info("Select: " + pageTitle);
            driver.findElement(CMM_LINK).click();
            return factoryPage.getPage(driver).render();
        }
        catch (NoSuchElementException te)
        {
            throw new PageException("Not able to find the Page" + pageTitle, te);
        }

    }

    /**
     * Does the current page have a manage-custom-models link in the Left Panel
     * 
     * @return boolean
     */
    public boolean hasManageModelsLink()
    {
        List<WebElement> elements = driver.findElements((CMM_LINK));
        return !elements.isEmpty();
    }
}
