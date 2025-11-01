<?php
/**
 * Created by PhpStorm.
 * User: s.kemper
 * Date: 29.03.2022
 * Time: 22:05
 *
 * This class does a lot of things... but has no productive purpose.
 * The only purpose of this class is to teach the basic functions
 * of PHP and how to use them.
 */

class MyFancyClass
{
    public function shortString($text, $length, $ending = '...')
    {
        if ($text === '') {
            return '';
        }

        if (strlen($ending) > $length) {
            return false;
        }

        if (strlen($text) <= $length) {
            return $text;
        }

        return substr($text, 0, $length - strlen($ending)) . $ending;
    }


    public function calcAverage($values)
    {
        if (empty($values)) return 0;
        $nums = [];
        foreach ($values as $val) {
            if (!is_numeric($val)) return false;
            $nums[] = (float)$val;
        }
        return array_sum($nums) / count($nums);
    }

// String to Array
// Array to Strings
// Object -> false
    public function getOpposite($value, $delimiter = ',')
    {
        if (is_object($value)) return false;
        if (is_array($value)) return implode($delimiter, $value);
        if (is_string($value)) {
            $parts = explode($delimiter, $value);
            return array_map('trim', $parts);
        }
        return false;
    }

}