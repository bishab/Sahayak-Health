import { TextInput, Text, Button } from "@mantine/core";
import { useModals } from "@mantine/modals";

function VerifyEmail() {
  const modals = useModals();

  const openContentModal = () => {
    const id = modals.openModal({
      title: "Subscribe to newsletter",
      children: (
        <>
          <TextInput label="Your email" />
          <Button fullWidth onClick={() => modals.closeModal(id)}>
            Submit
          </Button>
        </>
      ),
    });
  };

  return (
    {openContentModal}
  );
}
export default VerifyEmail;
