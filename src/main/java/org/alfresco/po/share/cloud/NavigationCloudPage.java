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
import org.alfresco.po.share.Navigation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represent elements found on the HTML page relating to the main navigation bar
 * 
 * @author Meenal Bhave
 * @since 1.0
 */
public class NavigationCloudPage extends Navigation
{
    private static final Log LOGGER = LogFactory.getLog(NavigationCloudPage.class);

    public static final String ADMIN_TOOLS_LINK_SELECTOR = "div#HEADER_ADMIN_CONSOLE";

    public static final String NON_ADMIN_TOOLS_LINK_SELECTOR = "div[id='HEADER_NON_ADMIN_ADMIN_CONSOLE']";

    public static final String MANAGE_CUSTOM_MODELS_LINK_SELECTOR = "tr[id='HEADER_CUSTOM_MODEL_MANAGER_CONSOLE']>td>a";

    /**
     * Constructor
     * 
     * @param drone WebDriver browser client
     */
    public NavigationCloudPage(WebDriver driver)
    {
        super();
    }

    /**
     * Select manage custom models link as Admin.
     * 
     * @return the html page
     */

    private HtmlPage selectManageCustomModelsRepoAdmin()
    {
        selectAdminTools().render();
        String selector = "ul.toolLink > li > span > a[href=\"custom-model-manager\"]";
        driver.findElement(By.cssSelector(selector)).click();
        return factoryPage.getPage(driver).render();
    }
}