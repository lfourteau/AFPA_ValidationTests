<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class UserController extends Controller
{
    public function loadUserReservationsAction(Request $request){
        $userId = $request->get('userId');
        $em = $this->getdoctrine()->getManager();
        $userReservations = $em->getRepository('AppBundle:Reservations')->findByUser(array(
            'id' => $userId
                ));
        
        return $this->render('appbundle/userReservations.html.twig', array(
            'userReservations' => $userReservations,
            ));
        
    }
    
    public function delReservationsAction (Request $request) {
        $em = $this->getdoctrine()->getManager();
        $documentId = $request->get('documentId');
        
        $reservationObject = $em->getRepository('AppBundle:Reservations')->findOneBy(array(
            'document' => $documentId
                ));
        $documentUp = $em->getRepository('AppBundle:Documents')->findOneBy(array(                           //maj table document pour indiquer qu'il n'est plus reservé
             'id' =>$documentId
             ));
        $documentUp->setBooked('no');
        
        $em->remove($reservationObject);
        $em->flush();

        return $this->render('appbundle/delReservationValidation.html.twig', array(
            
            ));
        
    }
}

   

