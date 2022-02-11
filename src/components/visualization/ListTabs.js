import React from "react";
import { useState } from "react";
import { Tabs } from "@mantine/core";
import Cases from "./Cases";

function ListTabs() {
  const [activeTab, setActiveTab] = useState(1);
  return (
    <div>
      <Tabs color="red" tabPadding="sm">
        <Tabs.Tab label="Cases">
          <Cases />
        </Tabs.Tab>
        <Tabs.Tab label="Active">
          <Cases type="activePerOneMillion" />
        </Tabs.Tab>
        <Tabs.Tab label="Critical">
          <Cases type="criticalPerOneMillion" />
        </Tabs.Tab>
        <Tabs.Tab label="Deaths">
          <Cases type="deaths" />
        </Tabs.Tab>
      </Tabs>
    </div>
  );
}

export default ListTabs;
