import React from 'react'
import { Paper,Card,Grid, UnstyledButton, Title,Space } from '@mantine/core';
import DoctorIcon from './DoctorIcon';
import Patient from './PatientIcon';
import ButtonDoctorIcon from './ButtonDoctorIcon';
import ButtonPatientIcon from './ButtonPatientIcon';



function SignupAs() {
  
  return (
    <div>

<Paper padding="md" shadow="xs">
    
<div align="center" >

{/* Title of the page */}
<Title order={1}>Login or Signup</Title>

<Space h="xl"/>
  {/* Unstyled buttons */}
    <Grid grow >
    <Grid.Col span={2}>
      <ButtonPatientIcon/>
      </Grid.Col>
  <Grid.Col span={2}>
 <ButtonDoctorIcon/>
  </Grid.Col>
    
    
        
        
        
        </Grid>
               </div>

</Paper>


    </div>
  )
}

export default SignupAs