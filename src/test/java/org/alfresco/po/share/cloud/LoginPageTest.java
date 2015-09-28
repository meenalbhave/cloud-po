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

import org.alfresco.po.share.SharePage;
import org.alfresco.po.share.cloud.user.AdminConsoleCloudPage;
import org.alfresco.po.share.cloud.AbstractTestCloud;
import org.alfresco.po.share.site.document.MyFilesPage;
import org.alfresco.test.FailedTestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Test Class holds all tests to test LoginPage
 * 
 * @author Meenal Bhave
 */
@Listeners(FailedTestListener.class)
public class LoginPageTest extends AbstractTestCloud
{
    private SharePage page;

    @BeforeClass(groups = { "alfresco-one" }, alwaysRun = true)
    public void setup() throws Exception
    {
        // page = loginAs(username, password);
    }


    /**
     * Navigate to Admin Tools Page from the dashboard page by Repo Admin
     * 
     * @throws Exception if error
     */
    @Test(groups = { "Enterprise-only" }, priority = 1)
    public void navigateToAdminTools() throws Exception
    {
        page = cloudActions.loginAs(driver, shareUrl, username, password).render();

        MyFilesPage myFilesPage = page.getNav().selectMyFilesPage().render();

        Assert.assertNotNull(myFilesPage);
    }
}
