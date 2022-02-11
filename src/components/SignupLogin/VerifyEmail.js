import React from "react";
import { Title, Card, Text, Input, Space } from "@mantine/core";

function VerifyEmail() {
  return (
    <div style={{ width: 800, margin: "auto" }}>
      <Card shadow="sm" padding="lg">
        <Title order={1}>Verify Your Email</Title>
        <Space h="xl" />
        <Text size="md">
          Please check your email for your verification code and enter it below
          to conform your email address.{" "}
        </Text>
        <Space h="xl" />
        <Input placeholder="Verification Code" />
      </Card>
    </div>
  );
}

export default VerifyEmail;
