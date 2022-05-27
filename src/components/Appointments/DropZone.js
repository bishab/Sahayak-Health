import { useState } from "react";
import { Group, Text, useMantineTheme } from "@mantine/core";
import { ImageIcon, UploadIcon, CrossCircledIcon } from "@modulz/radix-icons";
import { Dropzone, IMAGE_MIME_TYPE } from "@mantine/dropzone";
import { useNotifications } from '@mantine/notifications';

function ImageUploadIcon({ status, ...props }) {
  if (status.accepted) {
    return <UploadIcon {...props} />;
  }

  if (status.rejected) {
    return <CrossCircledIcon {...props} />;
  }

  return <ImageIcon {...props} />;
}

function getIconColor(status, theme) {
  return status.accepted
    ? theme.colors[theme.primaryColor][6]
    : status.rejected
    ? theme.colors.red[6]
    : theme.colorScheme === "dark"
    ? theme.colors.dark[0]
    : theme.black;
}
const fileToDataUri = (file) =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onload = (event) => {
      resolve(event.target.result);
    };
    reader.readAsDataURL(file);
  });





const Uploader = () => {
  const theme = useMantineTheme();
  const notifications = useNotifications();
  const [files, setFiles] = useState([]);

 const handleUseNotification = (message, description) => {
   console.log(message, description);
   try{

     notifications.showNotification({
              title: "File Uploaded",
              message:'Hey there, your file has been uploaded.! 😌',
            
            });

   }catch(e){
     console.log(e);
   }
   
    
  }
  
const handleFileChange =(file)=>{
  setFiles(file);
  handleUseNotification();
  
}
  return (
    // See results in console after dropping files to Dropzone
    <>
      <Dropzone
        onDrop={(files) =>handleFileChange(files[0])}
        onReject={(files) => console.log("rejected files", files)}
        maxSize={3 * 1024 ** 2}
        accept={IMAGE_MIME_TYPE}
      >
        {(status) => (
          <Group
            position="center"
            spacing="xl"
            style={{ minHeight: 220, pointerEvents: "none" }}
          >
            <ImageUploadIcon
              status={status}
              style={{
                width: 80,
                height: 80,
                color: getIconColor(status, theme),
              }}
            />

            <div>
              <Text size="xl" inline>
                Drag images here or click to select files
              </Text>
              <Text size="sm" color="dimmed" inline mt={7}>
                Attach as many files as you like, each file should not exceed
                5mb
              </Text>
            </div>
          </Group>
        )}
      </Dropzone>
      
      
    </>
  );
};
export default Uploader;
