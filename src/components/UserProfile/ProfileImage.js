import React from "react";
import { Image } from "@mantine/core";
function ProfileImage() {
  const imageDimension=150;
  return (
    <div>
      <Image
        src="https://images.unsplash.com/photo-1645700489470-044de618d002?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"
        height={imageDimension}
        width={imageDimension}
        radius="50%"
        alt="Profile Image"
        style={{ margin: "10px 0 20px 0" }}
      />
    </div>
  );
}

export default ProfileImage;
