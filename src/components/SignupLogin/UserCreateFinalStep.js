import React from "react";
import { Card, Title, Space, Text } from "@mantine/core";

function UserCreateFinalStep() {
  return (
    <div style={{ width: 800, margin: "auto" }}>
      <Card shadow="sm" padding="lg">
        <Title order={1}>Account Created</Title>
        <Title order={2}>Have a nice day.</Title>
        <Space h="xl" />

        <Text size="lg">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
          minim veniam, quis nostrud exercitation ullamco laboris nisi ut
          aliquip ex ea commodo consequat. Duis aute irure dolor in
          reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
          pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
          culpa qui officia deserunt mollit anim id est laborum.
        </Text>
      </Card>
    </div>
  );
}

export default UserCreateFinalStep;
