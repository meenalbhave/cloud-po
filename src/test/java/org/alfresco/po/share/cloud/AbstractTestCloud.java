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

import org.alfresco.po.AbstractTest;
import org.alfresco.po.share.FactoryPage;
import org.alfresco.po.share.SharePage;
import org.alfresco.po.share.cloud.steps.CloudActions;
import org.alfresco.po.share.cmm.steps.CmmActions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Abstract test cloud holds all common methods and functionality to create and run tests for CMM amp
 * 
 * @author Meenal Bhave
 */
@ContextConfiguration("classpath*:cloud-po-testContext.xml")

public abstract class AbstractTestCloud extends  AbstractTest
{
    private static final Log logger = LogFactory.getLog(AbstractTestCloud.class);
    
    protected CloudActions cloudActions = new CloudActions();
    @Autowired protected FactoryPage factoryPage;

    @BeforeClass(alwaysRun = true)
    @Parameters({ "contextFileName" })
    public void setup(@Optional("cloud-po-testContext.xml") String contextFileName) throws Exception
    {
        logger.debug("Starting Cloud Test Setup");
        logger.debug("Share URL: " + shareUrl);
    }
    
    public void cleanSession(WebDriver driver)
    {
        if (driver.getCurrentUrl().startsWith(shareUrl))
        {
            logout(driver);
        }
    }

    public SharePage loginAs(WebDriver driver, String username, String password) throws Exception
    {
        // Closes unexpected Popups, Dialogues to be able to access SharePage
        if (driver.getCurrentUrl().contains(shareUrl))
        {
        	cmmActions.closeSharePopUp(driver);
        }

        logout(driver);
        return loginAs(driver, shareUrl, username, password);
    }
}
