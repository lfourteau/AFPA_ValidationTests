<?php

namespace AppBundle\Repository;

use Doctrine\ORM\EntityRepository;

class CdsRepository extends EntityRepository {
    
    function findAllNewCDs ($durationValue) {
            $todayDate = new \DateTime();
            $dateInterval =new \DateInterval("P".$durationValue."D");
            $dateXDaysAgo = $todayDate->sub($dateInterval);           
        
            $qb = $this->createQueryBuilder('b');
            $qb   ->innerjoin('b.document', 'd')
                    ->innerjoin('d.documenttype', 'dtype')
                    ->where ('d.arrivalDate >= :dateXDaysAgo')                    
                    ->setParameter ('dateXDaysAgo', $dateXDaysAgo);
                   
            
            return $qb
                ->getQuery()
                ->getResult();
    }
    
    function findNewCDs ($durationValue, $typeId) {
            $todayDate = new \DateTime();
            $dateInterval =new \DateInterval("P".$durationValue."D");
            $dateXDaysAgo = $todayDate->sub($dateInterval);           
        
            $qb = $this->createQueryBuilder('b');
            $qb   ->innerjoin('b.document', 'd')
                    ->innerjoin('d.documenttype', 'dtype')
                    ->where ('d.arrivalDate >= :dateXDaysAgo')
                    ->andWhere('d.documenttype = :typeId')
                    ->setParameter ('dateXDaysAgo', $dateXDaysAgo)
                    ->setParameter ('typeId', $typeId);
            
            return $qb
                ->getQuery()
                ->getResult();
    }
}
