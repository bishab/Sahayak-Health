import { React, useState } from "react";
import { Stepper, Button, Group } from "@mantine/core";

import { UserCheck, MailOpened, ShieldCheck } from "tabler-icons-react";
import CreateUser from "./CreateUser";
import VerifyEmail from "./VerifyEmail";
import UserCreateFinalStep from "./UserCreateFinalStep";
function CreateUserSteps() {
  const [active, setActive] = useState(0);
  const nextStep = () =>
    setActive((current) => (current < 3 ? current + 1 : current));
  const prevStep = () =>
    setActive((current) => (current > 0 ? current - 1 : current));
  return (
    <div>
      <Stepper active={active} onStepClick={setActive} breakpoint="sm">
        <Stepper.Step
          icon={<UserCheck size={18} />}
          label="Fist step"
          description="Create an account"
          allowStepSelect={active > 0}
        >
          <CreateUser />
        </Stepper.Step>
        <Stepper.Step
          icon={<MailOpened size={18} />}
          label="Second step"
          description="Verify email"
          allowStepSelect={active > 1}
        >
          <VerifyEmail />
        </Stepper.Step>
        <Stepper.Step
          icon={<ShieldCheck size={18} />}
          label="Final step"
          description="Get full access"
          allowStepSelect={active > 2}
        >
          <UserCreateFinalStep />
        </Stepper.Step>
        {/* <Stepper.Completed>
          Completed, click back button to get to previous step
        </Stepper.Completed> */}
      </Stepper>

      <Group position="center" mt="xl">
        <Button variant="default" onClick={prevStep}>
          Back
        </Button>
        <Button onClick={nextStep}>Next step</Button>
      </Group>
    </div>
  );
}
//just for github
export default CreateUserSteps;
