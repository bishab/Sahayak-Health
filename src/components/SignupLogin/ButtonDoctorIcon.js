import React from 'react'
import { useNavigate, useLocation } from "react-router";
import { Paper,Card, UnstyledButton, Title,Space } from '@mantine/core';
import Doctor from "./DoctorIcon";

function ButtonDoctorIcon() {
    const navigate = new useNavigate();
  const location = useLocation().pathname;
  const onClickHandler = () => {
    navigate(`/createdoctor`);
  };
  return (
    <div align="left">
    <UnstyledButton onClick={onClickHandler}>
    <Card shadow="sm" padding="lg">
    <Doctor/>
<Title order={3} align="center" >I'm a Doctor</Title>

    </Card>

    </UnstyledButton>
    </div>
  )
}

export default ButtonDoctorIcon