<?php   
    namespace AppBundle\Repository;
      
    use Doctrine\ORM\EntityRepository;
    
    class ReservationRepository extends EntityRepository
    {
//        public function createReservation ($documentId, $em, $userId) {
//            $todayDate = new \DateTime();
//            
//            $qb = $em->createQueryBuilder();
//            $qb   ->insert('reservation')
//                    ->values(
//                            array(
//                                'reservation_date' => ':reservationDate',
//                                'user_id' => ':documentId',
//                                'document_id' => ':userId'
//                            )
//                        )
//                    ->setParameter('documentId', $documentId)
//                    ->setParameter('userId', $userId);
//                            
//            
//        }
    }