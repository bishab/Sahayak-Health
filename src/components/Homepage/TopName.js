import React from "react";
import {  Text, Space, Title } from "@mantine/core";

import TextLogo from "./TextLogo";
function TopName() {
  return (
    <div>
                  <Space h={40} />

      <Text size="xl" weight={700}>
        Welcome To,
      </Text>
      <TextLogo />

      {/* <Title order={1}>Sahayak Health</Title> */}
      <Space h={10} />
      <Text size="lg">
      Your all-in-one solution to the healthcare problems. Book an appointment, perform Covid-19 self-assessment, check live data and many more!
      </Text>
      <Space h={40} />

    </div>
  );
}

export default TopName;
