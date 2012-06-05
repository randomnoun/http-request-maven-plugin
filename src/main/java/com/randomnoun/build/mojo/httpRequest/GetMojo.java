package com.randomnoun.build.mojo.httpRequest;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.apache.maven.execution.MavenSession;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Goal which performs a HTTP GET.
 *
 * @goal get
 */
public class GetMojo
    extends AbstractMojo
{
	// from http://grepcode.com/file_/repo1.maven.org/maven2/org.kuali.maven.plugins/maven-cloudfront-plugin/1.1.0/org/kuali/maven/mojo/s3/BaseMojo.java/?v=source
	
	 /**
     * The Maven project this plugin runs in.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * @parameter expression="${settings}"
     * @required
     * @since 1.0
     * @readonly
     */
    private Settings settings;

    /**
     * @parameter default-value="${session}"
     * @required
     * @readonly
     */
    private MavenSession mavenSession;

    /*
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skipMojo()) {
            return;
        }
        executeMojo();
    }
    */
    
    
	/**
     * @parameter expression="${get.url}"
     */
    private URL url;

    public void execute()
        throws MojoExecutionException
    {
    	
    	Properties commandLine = getMavenSession().getExecutionProperties();
    	String cl_url = commandLine.getProperty("url");
    	if (cl_url!=null) { try {
			url = new URL(cl_url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MojoExecutionException("Malformed URL", e);
		} } 
    	
    	getLog().info("GET " + url.toString());
    	try {
    		url.getContent();
    	} catch (Exception e) {
    		getLog().info("Exception occurred retrieving URL", e);
    	}
    }


    /**
     * @return the project
     */
    public MavenProject getProject() {
        return project;
    }

    /**
     * @param project
     * the project to set
     */
    public void setProject(final MavenProject project) {
        this.project = project;
    }

    /**
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * @param settings
     * the settings to set
     */
    public void setSettings(final Settings settings) {
        this.settings = settings;
    }

    /**
     * @return the mavenSession
     */
    public MavenSession getMavenSession() {
        return mavenSession;
    }

    /**
     * @param mavenSession
     * the mavenSession to set
     */
    public void setMavenSession(final MavenSession mavenSession) {
        this.mavenSession = mavenSession;
    }
    
}
