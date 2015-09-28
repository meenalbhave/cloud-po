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

package org.alfresco.po.share.cloud.steps;

/**
 * Class contains Test steps / actions / utils for regression tests
 * 
 *  @author mbhave
 */

import org.alfresco.po.HtmlPage;
import org.alfresco.po.share.FactoryPage;
import org.alfresco.po.share.LoginPage;
import org.alfresco.po.share.util.PageUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
/**
 * Meenal please add documentation here.
 * 
 *
 */
public class CloudActions
{
    @Autowired protected FactoryPage factoryPage;
    
    public HtmlPage loginAs(final WebDriver driver, final String url, final String... userInfo) throws Exception
    {
        PageUtils.checkMandotaryParam("webdriver", driver);
        
        if(null == url||!url.startsWith("https://"))
        {
            throw new IllegalArgumentException("A valid shareUrl is required and can not be: " + url);
        }
        
        driver.navigate().to(url);
        LoginPage loginPage =  factoryPage.getPage(driver).render();
        return loginPage.loginAs(userInfo[0], userInfo[1]);
    }
    
    

}