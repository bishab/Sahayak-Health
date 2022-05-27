import React from 'react'
import { useNavigate, useLocation } from "react-router";
import { Paper,Card, UnstyledButton, Title,Space } from '@mantine/core';
import Patient from './PatientIcon';

function ButtonDoctorIcon() {
    const navigate = new useNavigate();
  const location = useLocation().pathname;
  const onClickHandler = () => {
    navigate(`/createuser`);
  };
  return (
    <div align="right">
    <UnstyledButton onClick={onClickHandler}>
    <Card shadow="sm" padding="lg">
    <Patient/>
<Title order={3} align="center" >I'm a Patient</Title>

    </Card>

    </UnstyledButton>
    </div>
  )
}

export default ButtonDoctorIcon