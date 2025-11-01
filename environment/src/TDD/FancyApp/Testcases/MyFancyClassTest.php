<?php
require_once __DIR__ . '/../Application/MyFancyClass.php';

use PHPUnit\Framework\TestCase;

class MyFancyClassTest extends TestCase
{
    private $_myFancyClass;

    public function setUp(): void
    {
        $this->_myFancyClass = new MyFancyClass();
    }

    public function testShortString()
    {
        $textEmpty = '';
        $text35Char = 'Ich bin Text mit 35 Char Länge (35)';
        $text130Char = 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam';
        $endingShort = '$';

        // leeren Text testen
        $this->assertEquals('', $this->_myFancyClass->shortString($textEmpty, 100));

        // Text kürzer als Länge → unverändert
        $this->assertEquals($text35Char, $this->_myFancyClass->shortString($text35Char, 100));

        // Text länger als Länge → gekürzt + Endung
        $this->assertStringEndsWith('...', $this->_myFancyClass->shortString($text130Char, 100));

        // Kürzung mit anderem Ending
        $this->assertStringEndsWith($endingShort, $this->_myFancyClass->shortString($text130Char, 129, $endingShort));
    }


    public function testCalcAverage()
    {
        $emptyValues = [];
        $average5 = [4.5, 5.0, 5.5];
        $valuesString = ['4.5', '5.0', '5.5'];
        $valuesWrong = ['3.44', 'bestanden', 'nicht bewertbar'];

        $this->assertEquals(0, $this->_myFancyClass->calcAverage($emptyValues));
        $this->assertEquals(5, $this->_myFancyClass->calcAverage($average5));
        $this->assertEquals($this->_myFancyClass->calcAverage($average5), $this->_myFancyClass->calcAverage($valuesString));
        $this->assertFalse($this->_myFancyClass->calcAverage($valuesWrong));
    }

//
    public function testGetOpposite()
    {
        $average5 = [4.5, 5.0, 5.5];
        $valuesString = ['4.5', '5.0', '5.5'];
        $stringList = 'Apfel,Birne,Pflaume,Pfirsich';
        $stringListSpace = 'Apfel, Birne, Pflaume, Pfirsich';

        $this->assertEquals('4.5,5,5.5', $this->_myFancyClass->getOpposite($average5));
        $this->assertEquals('4.5,5.0,5.5', $this->_myFancyClass->getOpposite($valuesString));

        $this->assertEquals($this->_myFancyClass->getOpposite($stringList), $this->_myFancyClass->getOpposite($stringList, ','));
        $this->assertEquals(
            $this->_myFancyClass->getOpposite($this->_myFancyClass->getOpposite($stringListSpace, ', '), ','),
            $stringList
        );
        $this->assertFalse($this->_myFancyClass->getOpposite($this->_myFancyClass));
    }

}
