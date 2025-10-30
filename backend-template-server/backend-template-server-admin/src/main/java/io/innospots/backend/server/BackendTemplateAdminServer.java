/*
 *  Copyright © 2021-2023 Innospots (http://www.innospots.com)
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.innospots.backend.server;

import io.innospots.ai.core.AiCoreImporter;
import io.innospots.base.server.ServiceRegistryHolder;
import io.innospots.base.server.ServiceType;
import io.innospots.libra.kernel.LibraKernelImporter;
import io.innospots.libra.security.LibraAuthImporter;
import io.innospots.project.console.ProjectConsoleImporter;
import io.innospots.schedule.console.ScheduleConsoleImporter;
import io.innospots.server.base.ServerConfigImporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Raydian
 * @date 2020/12/14
 */
@SpringBootApplication(exclude = {QuartzAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
        FreeMarkerAutoConfiguration.class})
@LibraAuthImporter
@LibraKernelImporter
@ProjectConsoleImporter
@ScheduleConsoleImporter
@ServerConfigImporter
@EnableScheduling
//@PluginConsoleImporter
public class BackendTemplateAdminServer {

    public static void main(String[] args) {
        ServiceRegistryHolder.serviceType(ServiceType.ALONE);
        SpringApplication.run(BackendTemplateAdminServer.class, args);
    }

}
