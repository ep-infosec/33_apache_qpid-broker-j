/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
define(["qpid/common/util",
        "dojo/query",
        "dojox/html/entities",
        "dojo/text!logger/memory/show.html",
        "qpid/management/logger/LogViewerWidget",
        "dojo/domReady!"], function (util, query, entities, template, LogViewerWidget) {
    function BrokerMemoryLogger(params)
    {
        var that = this;
        if (template)
        {
            util.parse(params.containerNode, template, function () {
                if (params.metadata)
                {
                    that.attributeContainers =
                        util.collectAttributeNodes(params.containerNode, params.metadata, "BrokerLogger", "Memory");
                    var options = {
                        controller: params.controller,
                        management: params.management,
                        userPreferences: params.management.userPreferences,
                        modelObj: params.modelObj,
                        maxRecords: params.data.maxRecords
                    };
                    that.logViewerWidget = new LogViewerWidget(options, params.typeSpecificDetailsNode);
                    that.update(params.data);
                }
            });
        }
    }

    BrokerMemoryLogger.prototype.update = function (restData) {
        util.updateAttributeNodes(this.attributeContainers, restData);
        this.logViewerWidget.maxRecords = restData.maxRecords;
        this.logViewerWidget.updateLogs();
    };

    return BrokerMemoryLogger;
});
