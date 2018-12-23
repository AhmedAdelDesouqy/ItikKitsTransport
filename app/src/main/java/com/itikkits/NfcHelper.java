package com.itikkits;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;

import java.io.UnsupportedEncodingException;

/**
 * * Created by Ahmed Adel Al-Desouqy on 24-Dec-18.
 */
public class NfcHelper {

    public static final String OUR_NDEF_MESSAGE_FINGERPRINT = "itikkits";

    public static boolean isOurTagConnected(Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            NdefRecord[] ndefRecords = readNdefMessageFromNfcTag(intent);

            if(ndefRecords!=null && ndefRecords.length>0) {
                for (NdefRecord ndefRecord : ndefRecords) {
                    String tagNdefMsg = getPlainTextFromNdefRecord(ndefRecord);
                    if(tagNdefMsg != null && tagNdefMsg.equals(OUR_NDEF_MESSAGE_FINGERPRINT)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public static String getPlainTextFromNdefRecord(NdefRecord ndefRecord) {
        String content = null;

        try {
            byte[] payload = ndefRecord.getPayload();
            String encoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTf-8";

            int languageSize = payload[0] & 0063;

            content = new String(payload, languageSize + 1, payload.length - languageSize - 1, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static NdefRecord[] readNdefMessageFromNfcTag(Intent intent) {
        final Tag tag = getNfcTagFromIntent(intent);
        if (tag == null)
            return null;

        try {
            Ndef ndef = Ndef.get(tag);
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            if (ndefMessage == null || ndefMessage.getRecords() == null)
                return new NdefRecord[0]; // Even if the Tag has no messages, return an empty array. Don't return null unless if the connection fails
            NdefRecord[] ndefRecords = ndefMessage.getRecords();                        // in order to give the method caller a hint about the problem
            ndef.close();
            return ndefRecords;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Tag getNfcTagFromIntent(Intent intent) {
        if (intent != null) {
            return intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
        return null;
    }

}
